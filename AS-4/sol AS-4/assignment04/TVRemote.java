package assignment04;

public class TVRemote {
	private TV tv;

	public TVRemote(TV tvIn) {
		tv = tvIn;
		while (tv.getChannel() > 2) {
            tv.down();
        }
	}

	public void setChannel(int newChannel) {
		if (newChannel > tv.MAX_CHANNELS) newChannel = tv.MAX_CHANNELS;
        if (newChannel < 2) newChannel = 2;

        while (tv.getChannel() != newChannel) {
            if (tv.getChannel() < newChannel) tv.up();
            else if (tv.getChannel() > newChannel) tv.down();
        }
	}
}

