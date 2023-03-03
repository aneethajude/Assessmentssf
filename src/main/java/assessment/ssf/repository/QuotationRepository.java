package assessment.ssf.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import assessment.ssf.model.*;

@Repository
public class QuotationRepository {


	@Autowired @Qualifier("pizza")
	private RedisTemplate<String, String> redisTemplate;

    
	public void save(Quotation quotation) {

        redisTemplate.opsForList().leftPush("contactlist", quotation.getQuoteId());
    redisTemplate.opsForHash().put("ordermap", quotation.getName(),quotation);

		// redisTemplate.opsForValue().set(
		// 		order.getQuoteId(), order.toJSON().toString()
		// );
	}

	public Optional<Quotation> get(String orderId) {
		String json = redisTemplate.opsForValue().get(orderId);

		if ((null == json) || (json.trim().length() <= 0))
			return Optional.empty();

		return Optional.of(Quotation.create(json));
	}
}
    

