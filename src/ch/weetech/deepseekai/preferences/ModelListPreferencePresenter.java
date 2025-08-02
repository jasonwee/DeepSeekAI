package ch.weetech.deepseekai.preferences;

import java.util.List;
import org.eclipse.jface.preference.IPreferenceStore;

public class ModelListPreferencePresenter {

    private final IPreferenceStore preferenceStore;
    private ModelListPreferencePage view;

    public ModelListPreferencePresenter(IPreferenceStore preferenceStore)
    {
        this.preferenceStore = preferenceStore;
    }

    public List<ModelApiDescriptor> getModels()
    {
        String modelsJson = preferenceStore.getString(PreferenceConstants.ASSISTAI_DEFINED_MODELS);
        List<ModelApiDescriptor> models =  ModelApiDescriptorUtilities.fromJson( modelsJson );
        return models;
    }

}
