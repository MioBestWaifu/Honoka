using Hestia.Controllers;

namespace Hestia.Pages;

public partial class MonitoringPage : ContentPage
{
    MonitoringController controller;
    public MonitoringPage()
    {
        InitializeComponent();
    }
    public MonitoringPage(MonitoringController controller)
	{
		InitializeComponent();
        this.controller = controller;
        this.BindingContext = controller;
    }

    protected override void OnSizeAllocated(double width, double height)
    {
        base.OnSizeAllocated(width, height);
        controller.Height = (int)height;
        controller.Width = (int)width;

    }
}