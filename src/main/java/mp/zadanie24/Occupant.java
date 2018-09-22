package mp.zadanie24;

import javax.persistence.*;

@Entity
public class Occupant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String LastName;

    private String gender;

    @ManyToOne
    private Flat flat;
}
