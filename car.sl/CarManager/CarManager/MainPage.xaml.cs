using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace CarManager
{
    public partial class MainPage : UserControl
    {
        public MainPage()
        {
            InitializeComponent();
        }

        private void btnSearchClick(object sender, System.Windows.RoutedEventArgs e)
        {
            mainContent.Children.Clear();
            mainContent.Children.Add(new SearchPage());
        }

        private void btnImport_Click(object sender, RoutedEventArgs e)
        {
            mainContent.Children.Clear();
            mainContent.Children.Add(new ImportPage());
        }
    }
}
