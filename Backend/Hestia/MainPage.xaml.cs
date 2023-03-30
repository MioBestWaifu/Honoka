namespace Hestia;

public partial class MainPage : ContentPage
{
    public Server server;

	public MainPage(Server server)
	{
		InitializeComponent();
        this.server = server;
	}

    private async void EnterInitialPage(object sender, EventArgs e)
    {

        await Shell.Current.GoToAsync($"{nameof(Tabs)}");
    }
}

