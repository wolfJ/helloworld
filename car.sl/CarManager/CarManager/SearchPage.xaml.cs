using System;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace CarManager
{
	public partial class SearchPage : UserControl
	{
		public SearchPage()
		{
			// 为初始化变量所必需
			InitializeComponent();
            this.Loaded += SearchPage_Loaded;
		}

        void SearchPage_Loaded(object sender, RoutedEventArgs e)
        {
            mainGrid.ItemsSource = TemplateModel.GenerateResource();
        }
	}
}