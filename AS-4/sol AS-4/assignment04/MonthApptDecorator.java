package assignment04;

public abstract class MonthApptDecorator implements MonthAppt {
	protected MonthAppt delegate;
	protected String description;
	protected double price;
	protected int dayOfMonth;
	protected int timeSlot;
	
	public MonthApptDecorator(MonthAppt delegate) {
		this.delegate = delegate;
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
		return price + delegate.getTotalPayments();
	}
	@Override
	public int dayTimeCount(int day, int slot) {
		int count = delegate.dayTimeCount(day, slot);
        if (day == dayOfMonth && slot == timeSlot) count += 1;
        return count;
	}
	@Override
	public String timeConlict() {
		if(dayTimeCount(dayOfMonth, timeSlot) > 1) 
			return "Time Conflict: " + toString() + "\n" +
			delegate.timeConlict();
		return delegate.timeConlict();
	}
	@Override
	public void printAppointments() {
		delegate.printAppointments();
		System.out.println(toString());
	}
	@Override
	public void printAppointments(int day) {
		delegate.printAppointments(day);
		if (day == dayOfMonth) {
            System.out.println(toString());
        }
        // delegate.printAppointments(day);
	}
	@Override
	public String toString() {
		return "On day " + dayOfMonth + " at " 
				+ timeSlot + ":00, appt. is for " + description;
	}
}
