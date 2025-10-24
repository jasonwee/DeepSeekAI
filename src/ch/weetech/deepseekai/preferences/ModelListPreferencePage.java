package ch.weetech.deepseekai.preferences;

public class ModelListPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	// inject
    private UISynchronize uiSync;
    
    private ModelListPreferencePresenter presenter;
    
    private Table      modelTable;

    private Text       apiUrl;

    private Text       apiKey;

    private Text       modelName;

    private Button     withVision;

    private Button     withFunctionCalls;

    private Scale      withTemperature;

    private Group      form;

    private Button     addButton;

    private Button     removeButton;

    @Override
    public void init(IWorkbench workbench) {
        presenter = Activator.getDefault().getModelsPreferencePresenter();

        // work around to get UISynchronize as PreferencePage does not seem to
        // be handled by the eclipse context
        IEclipseContext eclipseContext = workbench.getService(IEclipseContext.class);
        uiSync = eclipseContext.get(UISynchronize.class);
    }
}
