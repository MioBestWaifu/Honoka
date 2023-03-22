using Hestia.Controllers;

namespace Hestia.Pages;

public partial class ConfigPage : ContentPage
{
    ConfigController controller;
    public ConfigPage()
    {
        InitializeComponent();
    }
    public ConfigPage(ConfigController controller)
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

    private void Entry_Loaded(object sender, EventArgs e)
    {

    }

    private void Label_Loaded(object sender, EventArgs e)
    {

    }
}