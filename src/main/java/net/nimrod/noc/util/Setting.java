package net.nimrod.noc.util;

public class Setting<T> {
    
    private final String name;
    private final String description;
    private final T min;
    private final T max;

    private T value;

    public Setting(String name, String description, T value, T min, T max) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

	public T getMin() {
		return this.min;
	}

	public T getMax() {
		return this.max;
	}

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
