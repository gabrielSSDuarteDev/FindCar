package br.com.alura.FindCar.service;


import br.com.alura.FindCar.dto.UserRegisterDTO;
import br.com.alura.FindCar.model.User;
import br.com.alura.FindCar.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

   private String criptografarSenha(String senha){
        return passwordEncoder.encode(senha);
   }


    public void registerUser(UserRegisterDTO dto){
        if (userRepository.findByEmail(dto.email()).isPresent()){
            throw new RuntimeException("E-mail já cadastrado no sistema!");
        }
    String encryptedPassword = criptografarSenha(dto.password());


    User newUser = new User(dto.email(),encryptedPassword,dto.name());
    userRepository.save(newUser);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado" + username));
    }


    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuário com ID " + id + "não encontrado!"));
    }



}
