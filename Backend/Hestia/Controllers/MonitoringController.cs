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
        public Image test;

        [RelayCommand]
        void TryChangeImage()
        {
            double[] dataX = new double[] { 1, 2, 3, 4, 5 };
            double[] dataY = new double[] { 1, 4, 9, 16, 25 };
            var plt = new ScottPlot.Plot(400, 300);
            plt.AddScatter(dataX, dataY);
            //Isso funciona
            MemoryStream ms = new MemoryStream(plt.GetImageBytes());
            test.Source = ImageSource.FromStream(() => ms);
        }
    }
}
