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
}
