package it.volta.ts.pieuli.dijkstrapieuli.events;

public class DijkstraConsoleListener implements DijkstraListener
{
	@Override
	public void showMessage(DijkstraEvent e)
	{
		System.out.println(e.getSource());
	}
}
