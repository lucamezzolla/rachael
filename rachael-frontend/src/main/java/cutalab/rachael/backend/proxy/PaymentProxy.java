package cutalab.rachael.backend.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cutalab.rachael.backend.dto.GenericResponse;
import cutalab.rachael.backend.dto.wallet.PaymentListRequest;
import cutalab.rachael.backend.dto.wallet.PaymentListResponse;
import cutalab.rachael.backend.dto.wallet.PaymentRequest;
import cutalab.rachael.backend.dto.wallet.PaymentResponse;
import cutalab.rachael.config.FeignAuthInterceptor;
import cutalab.rachael.config.FeignConfig;

@FeignClient(name = "rachael-api-wallet", configuration = {FeignAuthInterceptor.class, FeignConfig.class})
public interface PaymentProxy {

    @PostMapping("/api/payment")
    ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request);

    @PostMapping("/api/payment/find")
    ResponseEntity<PaymentListResponse> getAllPayments(@RequestBody PaymentListRequest request);

    @GetMapping("/api/payment/{id}")
    ResponseEntity<PaymentResponse> getPaymentById(@PathVariable("id") Long id);

    @PutMapping("/api/payment/{id}")
    ResponseEntity<PaymentResponse> updatePayment(@PathVariable("id") Long id, @RequestBody PaymentRequest request);

    @DeleteMapping("/api/payment/{id}")
    ResponseEntity<GenericResponse> deletePayment(@PathVariable("id") Long id);

}
