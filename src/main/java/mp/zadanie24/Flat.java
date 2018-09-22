package mp.zadanie24;

import javax.persistence.*;
import java.util.List;

@Entity
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    private double area;

    @ManyToOne
    private Community community;

    @OneToMany(mappedBy = "flat")
    private List<Occupant> occupants;

}
