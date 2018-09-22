package mp.zadanie24;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CommunityController {

    private CommunityRepository communityRepository;

    public CommunityController(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
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
        Community community = optional.get();
        model.addAttribute("community", community);
        return "communityInfo";
    }
}