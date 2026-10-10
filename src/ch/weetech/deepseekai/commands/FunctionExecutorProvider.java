package ch.weetech.deepseekai.commands;

import org.eclipse.e4.core.di.annotations.Creatable;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Creatable
@Singleton
public class FunctionExecutorProvider {
	
	@Inject
	private FunctionCalls functionCalls;
	
	public FunctionExecutor get() {
		return new FunctionExecutor(functionCalls);
	}

}
