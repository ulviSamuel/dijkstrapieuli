package it.volta.ts.ulivisamuel.dijkstrapieuli.events;

public class DijkstraConsoleListener implements DijkstraListener
{
	@Override
	public void showMessage(DijkstraEvent e)
	{
		System.out.println(e.getSource());
	}
}
