package deepseekai;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import ch.weetech.deepseekai.preferences.ModelListPreferencePresenter;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "DeepSeekAI"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public PromptsPreferencePresenter getPromptsPreferencePresenter()
	{
		PromptsPreferencePresenter presenter = new PromptsPreferencePresenter( getDefault().getPreferenceStore() );
		return presenter;
	}

	public ModelListPreferencePresenter getModelsPreferencePresenter()
	{
		ModelListPreferencePresenter presenter = new ModelListPreferencePresenter( getDefault().getPreferenceStore() );
		return presenter;
	}


}
