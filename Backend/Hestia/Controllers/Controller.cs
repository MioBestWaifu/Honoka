using CommunityToolkit.Mvvm.ComponentModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Hestia.Controllers
{
    public abstract partial class Controller : ObservableObject
    {
        [ObservableProperty]
        [NotifyPropertyChangedFor(nameof(FortyOfWidth))]
        [NotifyPropertyChangedFor(nameof(ThirdOfWidth))]
        [NotifyPropertyChangedFor(nameof(FifthOfWidth))]
        [NotifyPropertyChangedFor(nameof(FifteenPercentOfWidth))]
        [NotifyPropertyChangedFor(nameof(HeightAspectForMonitoring))]
        int width;
        [ObservableProperty]
        [NotifyPropertyChangedFor(nameof(QuarterOfHeight))]
        [NotifyPropertyChangedFor(nameof(FifthOfHeight))]
        int height;

        [ObservableProperty]
        static int defaultMargin = 12; 
        public int FortyOfWidth => (int)(Width * 0.4);
        public int FifthOfWidth => (int)(Width * 0.2);
        public int HeightAspectForMonitoring => (int)(Width * 0.185625);
        public int FifteenPercentOfWidth => (int)(Width * 0.15);
        public int ThirdOfWidth => Width / 3;
        public int FifthOfHeight => Height / 5; 
        public int QuarterOfHeight => Height / 4;
    }
}
