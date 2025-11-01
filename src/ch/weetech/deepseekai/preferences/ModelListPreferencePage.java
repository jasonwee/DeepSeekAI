package ch.weetech.deepseekai.preferences;

import java.util.stream.Stream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

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

    @Override
    protected Control createContents(Composite parent) {
        // change orientation to HORIZONTAL for side-by-side layout
        var sashForm = new SashForm(parent, SWT.VERTICAL);
        sashForm.setLayoutData(new GridData(GridData.FILL_BOTH));

        // Composite for list and buttons
        Composite listButtonsComposite = new Composite(sashForm, SWT.NONE);
        listButtonsComposite.setLayout(new GridLayout(2, false));

        modelTable = new Table(listButtonsComposite, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
        modelTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        modelTable.setHeaderVisible(true);
        Stream.of("Url", "Model Name").forEach(columnName -> {
            TableColumn column = new TableColumn(modelTable, SWT.NULL);
            column.setText(columnName);
        });

        // Composite for buttons to align them vertically
        Composite buttonComposite = new Composite(listButtonsComposite, SWT.NONE);
        buttonComposite.setLayout(new GridLayout(1, false));
        buttonComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        removeButton = new Button(buttonComposite, SWT.NONE);
        removeButton.setText("Remove");
        removeButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // Model details in the bottom part of the sash form
        createModelDetails(sashForm);

        // Adjust the weights to allocate space (.e.g, 30% for list and buttons,
        // 70% for details)
        sashForm.setWeights(new int[] {1, 2});

        presenter.registerView(this);

        initializeListeners();
        clearModelDetails();

        return sashForm;
    }

    @Override
    protected void performApply() {
        int selectedIndex = modelTable.getSelectionIndex();
        ModelApiDescriptor updatedModel = new ModelApiDescriptor(
                "",
                "deepseek",
                apiUrl.getText(),
                apiKey.getText(),
                modelName.getText(),
                withTemperature.getSelection(),
                withVision.getSelection(),
                withFunctionCalls.getSelection());
        presenter.saveModel(selectedIndex, updatedModel);
        super.performApply();
    }
}
