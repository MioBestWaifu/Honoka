using Hestia.Controllers;
using System.Drawing;

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

    private void Image_Loaded(object sender, EventArgs e)
    {
        Microsoft.Maui.Controls.Image img = sender as Microsoft.Maui.Controls.Image;
        Bitmap map = new Bitmap("C:\\Users\\yan\\Pictures\\Wallpapers\\Oregairu-5.png");
        MemoryStream bs = new MemoryStream();
        map.Save(bs, System.Drawing.Imaging.ImageFormat.Png);
        img.Source = ImageSource.FromStream(() => bs);
    }
}