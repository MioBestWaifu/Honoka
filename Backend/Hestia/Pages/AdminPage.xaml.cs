namespace Hestia.Pages;

public partial class AdminPage : ContentPage
{
    AdminController controller;
    public AdminPage()
    {
        InitializeComponent();
    }
    public AdminPage(AdminController controller)
	{
		InitializeComponent();
        this.controller = controller;
        this.BindingContext= controller;
	}

    protected override void OnSizeAllocated(double width, double height)
    {
        base.OnSizeAllocated(width, height);
        controller.Height = (int)height;
        controller.Width = (int)width;

    }
}