package mp.zadanie24;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class OccupantController {

    private OccupantRepository occupantRepository;
    private FlatRepository flatRepository;

    public OccupantController(OccupantRepository occupantRepository, FlatRepository flatRepository) {
        this.occupantRepository = occupantRepository;
        this.flatRepository = flatRepository;
    }

    @GetMapping("/people")
    public String person(Model model) {
        List<Occupant> people = occupantRepository.findAll();
        model.addAttribute("people", people);
        return "people";
    }

    @GetMapping("/usunOsobe")
    public String delete(@RequestParam Long id) {
        Optional<Occupant> optional = occupantRepository.findById(id);
        if (optional.isPresent()) {
            Occupant occupant = optional.get();
            occupantRepository.delete(occupant);
        }
        return "redirect:/people";
    }

    @GetMapping("/dodajOsobe")
    public String add(Model model) {
        model.addAttribute("person", new Occupant());
        model.addAttribute("flats", flatRepository.findAll());
        return "addPerson";
    }

    @PostMapping("/dodajOsobe")
    public String add(Occupant occupant) {
        occupantRepository.save(occupant);
        return "redirect:/people";
    }

    @GetMapping("/edytujOsobe")
    public String edit(@RequestParam Long id, Model model) {
        Optional<Occupant> optional = occupantRepository.findById(id);
        if (optional.isPresent()) {
            Occupant occupant = optional.get();
            model.addAttribute("person", occupant);
            model.addAttribute("flats", flatRepository.findAll());
            return "editPerson";
        } else {
            return "redirect:/people";
        }
    }

    @PostMapping("/edytujOsobe")
    public String edit(Occupant occupant) {
        occupantRepository.save(occupant);
        return "redirect:/people";
    }
}
