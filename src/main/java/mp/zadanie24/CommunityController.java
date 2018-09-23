package mp.zadanie24;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CommunityController {

    private CommunityRepository communityRepository;
    private FlatRepository flatRepository;
    private OccupantRepository occupantRepository;

    public CommunityController(CommunityRepository communityRepository, FlatRepository flatRepository, OccupantRepository occupantRepository) {
        this.communityRepository = communityRepository;
        this.flatRepository = flatRepository;
        this.occupantRepository = occupantRepository;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/communities")
    public String community(Model model){
        List<Community> communities = communityRepository.findAll();
        model.addAttribute("communities", communities);
        return "communities";
    }

    @GetMapping("/usunWspolnote")
    public String delete(@RequestParam Long id) {
        Optional<Community> optional = communityRepository.findById(id);
        if(optional.isPresent()) {
            Community community = optional.get();
        communityRepository.delete(community);
        }
        return "redirect:/communities";
    }

    @GetMapping("/dodajWspolnote")
    public String add(Model model) {
        model.addAttribute("community", new Community());
        return "addCommunity";
    }

    @PostMapping("/dodajWspolnote")
    public String add(Community community) {
        if(community.getName().equals("")){
            community.setName(community.getAddress());
        }
        communityRepository.save(community);
        return "redirect:/communities";
    }

    @GetMapping("/edytujWspolnote")
    public String edit(@RequestParam Long id, Model model) {
        Optional<Community> optional = communityRepository.findById(id);
        if(optional.isPresent()) {
            Community community = optional.get();
            model.addAttribute("community", community);
            model.addAttribute("communities", communityRepository.findAll());
            return "editCommunity";
        } else {
            return "redirect:/communities";
        }
    }

    @PostMapping("/edytujWspolnote")
    public String edit(Community community) {
        communityRepository.save(community);
        return "redirect:/communities";
    }

    @GetMapping("/community")
    public String info(@RequestParam Long id, Model model) {
        Optional<Community> optional = communityRepository.findById(id);
        if(optional.isPresent()) {
        Community community = optional.get();
        model.addAttribute("community", community);
        List<Flat> communityFlats = community.getFlats();
        model.addAttribute("flats", communityFlats);
        List<Occupant> occupants = occupantRepository.findAll();
        List<Occupant> flatOccupants = new ArrayList<>();
        double sum = 0;
            for (int i = 0; i < communityFlats.size(); i++) {
                Flat flat = communityFlats.get(i);
                sum = sum + flat.getArea();
                for (int j = 0; j < occupants.size(); j++) {
                    Occupant occupant = occupants.get(j);
                    if(occupant.getFlat().equals(flat)){
                        flatOccupants.add(occupant);
                    }
                }
            }
            model.addAttribute("sum", sum);
            model.addAttribute("occupants", flatOccupants);
            return "communityInfo";
        } else return "redirect:/communities";
    }
}