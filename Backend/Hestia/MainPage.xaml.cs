namespace Hestia;

public partial class MainPage : ContentPage
{

	public MainPage()
	{
		InitializeComponent();
	}

    private async void EnterInitialPage(object sender, EventArgs e)
    {

        await Shell.Current.GoToAsync($"{nameof(Tabs)}");
    }
}

