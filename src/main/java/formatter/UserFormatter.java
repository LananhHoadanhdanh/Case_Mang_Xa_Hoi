package formatter;



import model.entity.ImageUser;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import service.imageUse.IImageUseService;
import service.user.IUserService;
import service.user.UserService;


import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class UserFormatter implements Formatter<User> {

    private IUserService userService;

    @Autowired
    public UserFormatter(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        Optional<User> userServiceOptional = userService.findById(Long.parseLong(text));
        return userServiceOptional.orElse(null);
    }

    @Override
    public String print(User object, Locale locale) {
        return "[" +object.getGender() + ", " + object.getDateOfBirth() + object.getId() + ", " +object.getAddress() + ", " +object.getFullName() + ", " + object.getNumberPhone()+", " +object.getEmail()+ "]";
    }
}