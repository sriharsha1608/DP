package assignment04;

public class ThermostatRemote {
	private Thermostat stat;
	public ThermostatRemote(Thermostat statIn) {
		stat = statIn;
		while (stat.getTemperature() > stat.MIN_TEMPERATURE) {
            stat.cooler();
        }
	}

	public void setTemperature(int newTemp){
		if (newTemp > stat.MAX_TEMPERATURE) newTemp = stat.MAX_TEMPERATURE;
        if (newTemp < stat.MIN_TEMPERATURE) newTemp = stat.MIN_TEMPERATURE;

        while (stat.getTemperature() != newTemp) {
            if (stat.getTemperature() < newTemp) stat.warmer();
            else if (stat.getTemperature() > newTemp) stat.cooler();
        }

	}
}

