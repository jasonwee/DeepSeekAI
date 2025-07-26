package ch.weetech.deepseekai.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

public class ModelListPreferencePresenter {

    private final IPreferenceStore preferenceStore;
    private ModelListPreferencePage view;

    public ModelListPreferencePresenter(IPreferenceStore preferenceStore)
    {
        this.preferenceStore = preferenceStore;
    }

}
