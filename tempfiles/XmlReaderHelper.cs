using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace Pet.Kit
{
    public class XmlReaderHelper
    {
        //get xlmElement.
        public static bool MoveToContent(XmlReader xml, string element)
        {


            if (string.IsNullOrEmpty(element))
                throw new ArgumentNullException();
            if ((xml.MoveToContent() == XmlNodeType.Element && xml.Name == element))
            {
                xml.Read();
                xml.MoveToContent();
                return true;
            }
            else
                return false;

        }

        public static bool MoveNextElement(XmlReader xml, string element)
        {
            xml.MoveToContent();
            do
            {
                if (xml.NodeType == XmlNodeType.EndElement && xml.Name == element)
                {
                    xml.Read();
                    xml.MoveToContent();
                    return true;
                }
            } while (xml.Read());
            return false;
        }


        internal static bool MoveToElementX(XmlReader xml, string element)
        {
            if (xml.NodeType != XmlNodeType.Element)
                xml.MoveToContent();
            var currentElement = xml.Name;


            do
            {
                if (xml.MoveToContent() == XmlNodeType.Element && xml.Name == element)
                {
                    xml.Read();
                    return true;
                }

                if (xml.NodeType == XmlNodeType.EndElement && xml.Name == currentElement)
                {
                    xml.Read();
                    return false;
                }
            } while (xml.Read());

            return false;
        }

        internal static bool MoveToElement(XmlReader xml, string element)
        {
            return xml.MoveToContent() == XmlNodeType.Element && xml.Name == element;
        }
    }
}
