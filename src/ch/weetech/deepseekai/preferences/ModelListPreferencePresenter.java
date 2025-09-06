package ch.weetech.deepseekai.preferences;

import java.util.List;
import java.util.Optional;

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

    public Optional<ModelApiDescriptor> getModelAt( int index )
    {
        var models = getModels();
        return index >= 0 && index < models.size() ? Optional.of(models.get( index )) : Optional.empty();
    }

    public void addModel()
    {
        view.clearModelSelection();
        view.clearModelDetails();
        view.setDetailsEditable( true );
    }

    public void removeModel( int selectedIndex )
    {
        var models = getModels();
        if ( selectedIndex >= 0 && selectedIndex < models.size() )
        {
            models.remove( selectedIndex );
            save( models );
            view.showModels( models );
            view.clearModelDetails();
        }
    }

    public void save(List<ModelApiDescriptor> models) {
        String json = ModelApiDescriptorUtilities.toJson(models);
        preferenceStores.setValue(PreferenceConstants.ASSISTAI_DEFINED_MODELS, json);
    }


}
