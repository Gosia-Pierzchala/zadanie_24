package mp.zadanie24;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class FlatController {

    private FlatRepository flatRepository;
    private CommunityRepository communityRepository;

    public FlatController(FlatRepository flatRepository, CommunityRepository communityRepository) {
        this.flatRepository = flatRepository;
        this.communityRepository = communityRepository;
    }

    @GetMapping("/flats")
    public String flat(Model model){
        List<Flat> flats = flatRepository.findAll();
        model.addAttribute("flats", flats);
        return "flats";
    }

    @GetMapping("/usunMieszkanie")
    public String delete(@RequestParam Long id) {
        Optional<Flat> optional = flatRepository.findById(id);
        if(optional.isPresent()) {
            Flat flat = optional.get();
            flatRepository.delete(flat);
        }
        return "redirect:/flats";
    }

    @GetMapping("/dodajMieszkanie")
    public String add(Model model) {
        model.addAttribute("flat", new Flat());
        model.addAttribute("communities", communityRepository.findAll());
        return "addFlat";
    }

    @PostMapping("/dodajMieszkanie")
    public String add(Flat flat) {
        flatRepository.save(flat);
        return "redirect:/flats";
    }

    @GetMapping("/edytujMieszkanie")
    public String edit(@RequestParam Long id, Model model) {
        Optional<Flat> optional = flatRepository.findById(id);
        if(optional.isPresent()) {
            Flat flat = optional.get();
            model.addAttribute("flat", flat);
            model.addAttribute("flats", flatRepository.findAll());
            model.addAttribute("communities", communityRepository.findAll());
            return "editFlat";
        } else {
            return "redirect:/flats";
        }
    }

    @PostMapping("/edytujMieszkanie")
    public String edit(Flat flat) {
        flatRepository.save(flat);
        return "redirect:/flats";
    }

    @GetMapping("/flat")
    public String info(@RequestParam Long id, Model model) {
        Optional<Flat> optional = flatRepository.findById(id);
        if(optional.isPresent()) {
            Flat flat = optional.get();
            model.addAttribute("flat", flat);
            return "flatInfo";
        } else return "redirect:/flats";
    }
}