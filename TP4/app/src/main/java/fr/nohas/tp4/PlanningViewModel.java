package fr.nohas.tp4;

import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//classe planning qui hérite de view model
public class PlanningViewModel extends ViewModel {

    private MutableLiveData<String> liveData_8h=new MutableLiveData<>("Rencontre client Dupont\n");
    private MutableLiveData<String> liveData_10h=new MutableLiveData<>("Travailler le dossier recrutement\n");
    private MutableLiveData<String> liveData_14h=new MutableLiveData<>("Réunion équipe\n");
    private MutableLiveData<String> liveData_16h=new MutableLiveData<>("Préparation dossier vente\n");

    //-----------------------getters------------

    public MutableLiveData<String> getLiveData_8h() {
        if(liveData_8h==null)
            liveData_8h = new MutableLiveData<String>();
        return liveData_8h;
    }

    public MutableLiveData<String> getLiveData_10h() {
        if(liveData_10h==null)
            liveData_10h = new MutableLiveData<String>();
        return liveData_10h;
    }

    public MutableLiveData<String> getLiveData_14h() {
        if(liveData_14h==null)
            liveData_14h = new MutableLiveData<String>();
        return liveData_14h;
    }

    public MutableLiveData<String> getLiveData_16h() {
        if(liveData_16h==null)
            liveData_16h = new MutableLiveData<String>();
        return liveData_16h;
    }

    //-----------Setters-------------------

    public void setLiveData_8h(String tache) {
        this.liveData_8h.setValue(tache);
    }

    public void setLiveData_10h(String tache) {
        this.liveData_10h.setValue(tache);
    }

    public void setLiveData_14h(String tache) {
        this.liveData_14h.setValue(tache);
    }

    public void setLiveData_16h(String tache) {
        this.liveData_16h.setValue(tache);
    }
}
