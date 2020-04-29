package business;

import enums.Failures;

public interface IService {
	public Failures verify(String[] list);
	public void store(String[] list);
	public Object create(String [] list);
}
