using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using ProjectMercury;
using ProjectMercury.Emitters;
using ProjectMercury.Modifiers;

namespace Pet.Kit
{
    public class ParticleReader : ResourceReader<ParticleEffect>
    {
        private XmlReader Xml;


        public override ParticleEffect Read(ResourceManager manager, System.IO.Stream stream)
        {
            Xml = XmlReader.Create(stream);


            ParticleEffect value = new ParticleEffect();

            while (XmlReaderHelper.MoveToElementX(Xml, "Item"))
                value.Add(EimtterReader.ReadEmitter(Xml));

            //value.Name = input.Xml.ReadElementString("Name");
            //value.Author = input.Xml.ReadElementString("Author");
            //value.Description = input.Xml.ReadElementString("Description");

            //while (input.MoveToElement("Controller"))
            //    value.Controllers.Add(input.ReadObject<Controller>(new ContentSerializerAttribute { ElementName = "Controller" }));

            return value;
        }

        private bool MoveToElement(string element)
        {
            return false;
        }

    }

    internal class EimtterReader
    {
        public static Emitter ReadEmitter(XmlReader xml)
        {
            Emitter emitter = new Emitter();

            emitter.Name = xml.ReadElementString("Name");
            emitter.Budget = int.Parse(xml.ReadElementString("Budget"));
            emitter.Term = float.Parse(xml.ReadElementString("Term"));
            emitter.ReleaseQuantity = int.Parse(xml.ReadElementString("ReleaseQuantity"));
            emitter.Enabled = bool.Parse(xml.ReadElementString("Enabled"));

            emitter.ReleaseSpeed = ReadVariableFloat(xml, "ReleaseSpeed");
            emitter.ReleaseColour = ReadVariableFloat3(xml, "ReleaseColour");
            emitter.ReleaseOpacity = ReadVariableFloat(xml, "ReleaseOpacity");
            emitter.ReleaseScale = ReadVariableFloat(xml, "ReleaseScale");
            emitter.ReleaseRotation = ReadVariableFloat(xml, "ReleaseRotation");
            emitter.ReleaseImpulse = ReadVariableFloat2(xml.ReadElementString("ReleaseImpulse"));
            emitter.ParticleTextureAssetName = xml.ReadElementString("ParticleTextureAssetName");
            emitter.Modifiers = ReadModifiers(xml, "Modifiers");

            return emitter;
        }

        private static ModifierCollection ReadModifiers(XmlReader xml, string element)
        {
            if (XmlReaderHelper.MoveToContent(xml, element))
            {
                var modifiers = new ModifierCollection();
                while (XmlReaderHelper.MoveToElement(xml, "Item"))
                    modifiers.Add(EimtterReader.ReadModifier(xml));
                return modifiers;
            }
            else
                return null;
        }

        private static Modifier ReadModifier(XmlReader xml)
        {
            if (xml.MoveToAttribute("Type"))
            {
                try
                {
                    var modifierType = xml.Value;
                    modifierType = modifierType.Substring(modifierType.IndexOf(':') + 1);
                    if ("TrajectoryRotationModifier" == modifierType)
                    {
                        return ReadTrajectoryRotationModifier(xml, modifierType);
                    }
                    else if ("ScaleModifier" == modifierType)
                    {
                        return ReadScaleModifier(xml, modifierType);
                    }
                    else
                    {
                        return ReadModifier(xml, modifierType);
                    }
                }
                finally
                {
                    XmlReaderHelper.MoveNextElement(xml, "Item");
                }




            }
            else
                return null;
        }

        private static ScaleModifier ReadScaleModifier(XmlReader xml, string modifierType)
        {
            ScaleModifier modifier = (ScaleModifier)Activator.CreateInstance(typeof(ScaleModifier));
            if (XmlReaderHelper.MoveToContent(xml, "Item"))
            {
                modifier.InitialScale = float.Parse(xml.ReadElementString("InitialScale"));
                modifier.UltimateScale = float.Parse(xml.ReadElementString("UltimateScale"));
            }
            return modifier;
        }

        private static TrajectoryRotationModifier ReadTrajectoryRotationModifier(XmlReader xml, string modifierType)
        {
            TrajectoryRotationModifier modifier = (TrajectoryRotationModifier)Activator.CreateInstance(typeof(TrajectoryRotationModifier));
            if (XmlReaderHelper.MoveToContent(xml, "Item"))
            {
                modifier.RotationOffset = float.Parse(xml.ReadElementString("RotationOffset"));
            }
            return modifier;
        }

        private static Modifier ReadModifier(XmlReader xml, string modifierType)
        {
            System.Reflection.Assembly assembly = typeof(Modifier).Assembly;
            var type = assembly.GetType(typeof(Modifier).Namespace + "." + modifierType);
            var modifier = Activator.CreateInstance(type);

            if (XmlReaderHelper.MoveToContent(xml, "Item"))
            {
                string elementName;
                string elementValue;
                while ((elementValue = readElementValue(xml, out elementName)) != null)
                {
                    Type eleType;
                    var field = type.GetField(elementName);
                    var property = type.GetProperty(elementName);
                    if (field != null)
                        eleType = field.FieldType;
                    else
                        eleType = property.PropertyType;

                    object fieldValue = elementValue;

                    if (eleType == typeof(string))
                    {

                    }
                    else if (eleType == typeof(bool))
                    {
                        fieldValue = bool.Parse(elementValue);
                    }
                    else if (eleType == typeof(int))
                    {
                        fieldValue = int.Parse(elementValue);
                    }
                    else if (eleType == typeof(float))
                    {
                        fieldValue = float.Parse(elementValue);
                    }
                    else if (eleType == typeof(double))
                    {
                        fieldValue = double.Parse(elementValue);
                    }
                    else if (eleType == typeof(Vector2))
                    {
                        fieldValue = ReadVariableFloat2(elementValue);
                    }
                    else if (eleType == typeof(Vector3))
                    {
                        fieldValue = parseVector3(elementValue);
                    }
                    if (field != null)
                        field.SetValue(modifier, fieldValue);
                    else
                        property.SetValue(modifier, fieldValue, null);
                }
            }
            return (Modifier)modifier;
        }

        private static string readElementValue(XmlReader xml, out string elementName)
        {
            if (xml.Name == "Item" && xml.NodeType == XmlNodeType.EndElement)
            {
                xml.Read();
                elementName = null;
                return null;
            }

            elementName = xml.Name;
            xml.Read();
            var value = xml.Value;
            xml.Read();
            return value;
        }


        private static VariableFloat3 ReadVariableFloat3(XmlReader xml, string element)
        {
            var variable = new VariableFloat3();
            if (XmlReaderHelper.MoveToContent(xml, element))
            {
                variable.Value = parseVector3(xml.ReadElementString("Value"));
                variable.Variation = parseVector3(xml.ReadElementString("Variation"));
                XmlReaderHelper.MoveNextElement(xml, element);
            }
            return variable;
        }

        private static VariableFloat ReadVariableFloat(XmlReader xml, string element)
        {
            var variable = new VariableFloat();
            if (XmlReaderHelper.MoveToContent(xml, element))
            {
                variable.Value = float.Parse(xml.ReadElementString("Value"));
                variable.Variation = float.Parse(xml.ReadElementString("Variation"));
                XmlReaderHelper.MoveNextElement(xml, element);
            }
            return variable;
        }


        private static Vector2 ReadVariableFloat2(string str)
        {
            var v2 = str.Split((string[])null, StringSplitOptions.RemoveEmptyEntries);
            Vector2 value = new Vector2(float.Parse(v2[0]), float.Parse(v2[1]));
            return value;
        }

        private static Vector3 parseVector3(string valueV3)
        {
            var v3 = valueV3.Split((string[])null, StringSplitOptions.RemoveEmptyEntries);
            Vector3 value = new Vector3(float.Parse(v3[0]), float.Parse(v3[1]), float.Parse(v3[1]));
            return value;
        }

    }
}
