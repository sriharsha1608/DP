package assignment04;

public abstract class ApptDecorator implements ApptsForMonth {
	protected ApptsForMonth next;
	protected String description;
	protected double price;
	protected int dayOfMonth;
	protected int timeSlot;
	
	public ApptDecorator(ApptsForMonth delegate) {
		this.next = delegate;
	}
	@Override
	public String getDescription() {
		return description;
	}
	@Override
	public double getPrice() {
		return price;
	}
	@Override
	public int getDayOfMonth() {
		return dayOfMonth;
	}
	@Override
	public int getTimeSlot() {
		return timeSlot;
	}
	@Override
	public double getTotalPayments() {
		return price + next.getTotalPayments();
	}
	@Override
	public int dayTimeCount(int day, int slot) {
		int count = next.dayTimeCount(day, slot);
        if (day == dayOfMonth && slot == timeSlot) count += 1;
        return count;
	}
	@Override
	public String timeConlict() {
		if(dayTimeCount(dayOfMonth, timeSlot) > 1) 
			return "Time Conflict: " + toString() + "\n" +
			next.timeConlict();
		return next.timeConlict();
	}
	@Override
	public void printAppointments() {
		next.printAppointments();
		System.out.println(toString());
	}
	@Override
	public void printAppointments(int day) {
		next.printAppointments(day);
		if (day == dayOfMonth) {
            System.out.println(toString());
        }
        // next.printAppointments(day);
	}
	@Override
	public String toString() {
		return "On day " + dayOfMonth + " at " 
				+ timeSlot + ":00, appt. is for " + description;
	}
}
