package Entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-16T16:55:46", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> biometricData;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> userType;
    public static volatile SingularAttribute<User, Long> userId;
    public static volatile SingularAttribute<User, String> curp;
    public static volatile SingularAttribute<User, Integer> age;

}