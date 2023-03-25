using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using ScottPlot;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Hestia.Controllers
{
    public partial class MonitoringController : Controller
    {
        int i = 1;
        public Image test;
        [ObservableProperty]
        string maxConn = "Max Connections: 85", avgConn = "Avg Connections: 21", minConn = "Min Connections: 8", maxData = "Max Data: 28 mb",
            avgData = "Avg Data: 5,8 mb", minData = "Min Data: 1,2 mb", maxErrors = "Max Errors: 52", avgErros = "Avg Errors: 11", minErrors = "Min Errors 2";

        [RelayCommand]
        void TryChangeImage()
        {
            double[] dataX = new double[] { 1, 2, 3*i, 4, 5/i };
            double[] dataY = new double[] { 1, 4, 9*i, 16, 25/i };
            var plt = new ScottPlot.Plot(400, 300);
            plt.AddScatter(dataX, dataY);
            //Isso funciona!!!!
            MemoryStream ms = new MemoryStream(plt.GetImageBytes());
            test.Source = ImageSource.FromStream(() => ms);
            i++;
        }
    }
}
