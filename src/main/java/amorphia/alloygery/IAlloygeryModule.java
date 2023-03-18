package amorphia.alloygery;

public interface IAlloygeryModule
{
	default boolean shouldInitialize()
	{
		return true;
	}

	void initialize();

	void initializeClient();
}
