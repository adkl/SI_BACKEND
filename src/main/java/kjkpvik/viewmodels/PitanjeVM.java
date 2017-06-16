package kjkpvik.viewmodels;

import kjkpvik.models.Pitanje;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adnan on 5/24/2017.
 */
public class PitanjeVM {
    private Long id;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public static List<PitanjeVM> fromPitanjaList(List<Pitanje> pitanjaList) {
        List<PitanjeVM> pitanjaVM = new ArrayList<>();
        pitanjaList.forEach(p -> {
            PitanjeVM pitanjeVM = new PitanjeVM();
            pitanjeVM.setId(p.getID());
            pitanjeVM.setText(p.getTekst());
            pitanjaVM.add(pitanjeVM);
        });
        return pitanjaVM;
    }
}
