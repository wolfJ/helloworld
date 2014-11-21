using System;
using System.Collections.Generic;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace CarManager
{
    public class TemplateModel
    {
        public string 车牌 { get; set; }
        public string 车主 { get; set; }
        public string 电话 { get; set; }
        public string 车辆品牌 { get; set; }
        public string 车辆型号 { get; set; }
        public string 发动机 { get; set; }
        public string 车架号 { get; set; }
        public string 登记日期 { get; set; }
        public string 保险日期 { get; set; }
        public string 省份证 { get; set; }
        public string 地址 { get; set; }


        public static List<TemplateModel> GenerateResource()
        {
            var list = new List<TemplateModel>();
            list.Add(new TemplateModel() { 车牌 = "苏AN331D", 车主 = "郭玥", 车辆型号 = "CA5043XXYP40K2L1EA84-3", 电话 = "13851888197", 发动机 = "01991983", 车架号 = "LFNA4LBA7DAD83141", 保险日期 = "2014/1/4", 车辆品牌 = "一汽解放青岛汽车有限公司" });
            list.Add(new TemplateModel() { 车牌 = "苏AN331D", 车主 = "郭玥", 车辆型号 = "CA5043XXYP40K2L1EA84-3", 电话 = "13851888197", 发动机 = "01991983", 车架号 = "LFNA4LBA7DAD83141", 保险日期 = "2014/1/4", 车辆品牌 = "一汽解放青岛汽车有限公司" });
            list.Add(new TemplateModel() { 车牌 = "苏AN331D", 车主 = "郭玥", 车辆型号 = "CA5043XXYP40K2L1EA84-3", 电话 = "13851888197", 发动机 = "01991983", 车架号 = "LFNA4LBA7DAD83141", 保险日期 = "2014/1/4", 车辆品牌 = "一汽解放青岛汽车有限公司" });
            list.Add(new TemplateModel() { 车牌 = "苏AN331D", 车主 = "郭玥", 车辆型号 = "CA5043XXYP40K2L1EA84-3", 电话 = "13851888197", 发动机 = "01991983", 车架号 = "LFNA4LBA7DAD83141", 保险日期 = "2014/1/4", 车辆品牌 = "一汽解放青岛汽车有限公司" });
            list.Add(new TemplateModel() { 车牌 = "苏AN331D", 车主 = "郭玥", 车辆型号 = "CA5043XXYP40K2L1EA84-3", 电话 = "13851888197", 发动机 = "01991983", 车架号 = "LFNA4LBA7DAD83141", 保险日期 = "2014/1/4", 车辆品牌 = "一汽解放青岛汽车有限公司" });
            list.Add(new TemplateModel() { 车牌 = "苏AN331D", 车主 = "郭玥", 车辆型号 = "CA5043XXYP40K2L1EA84-3", 电话 = "13851888197", 发动机 = "01991983", 车架号 = "LFNA4LBA7DAD83141", 保险日期 = "2014/1/4", 车辆品牌 = "一汽解放青岛汽车有限公司" });
            return list; 
        }
    
    }

}