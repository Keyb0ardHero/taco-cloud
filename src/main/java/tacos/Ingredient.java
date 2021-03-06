package tacos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * @author keyboardhero
 * @create 2022-05-19 20:37
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
@Entity
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    //修正：增加此注解
    @Enumerated(EnumType.STRING)
    private final Type type;

    public static enum Type{
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
    }
}
