package africa.semicolon.wollet.service;

import africa.semicolon.wollet.dto.response.DepositResponse;
import africa.semicolon.wollet.dto.request.CustomerDepositRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WollectCustomerService implements CustomerService {

//    @Autowired
//    private final WallectService wallectService;

    @Override
    public DepositResponse deposit(CustomerDepositRequest request) {
        return null;
    }
}
