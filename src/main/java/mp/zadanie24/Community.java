package mp.zadanie24;

import javax.persistence.*;
import java.util.List;

@Entity
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "community")
    private List<Flat> flats;
}
