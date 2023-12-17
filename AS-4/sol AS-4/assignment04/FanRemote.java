package assignment04;

public class FanRemote {
	private Fan fan;
	public FanRemote(Fan fanIn) {
		fan = fanIn;
		while (fan.getSpeed() > 0) {
            fan.slower();
        }
	}

	public void setSpeed(int newSpeed) {
		if (newSpeed > fan.MAX_SPEED) newSpeed = fan.MAX_SPEED;
        if (newSpeed < 0) newSpeed = 0;

        while (fan.getSpeed() != newSpeed) {
            if (fan.getSpeed() < newSpeed) fan.faster();
            else if (fan.getSpeed() > newSpeed) fan.slower();
        }
	}

}

