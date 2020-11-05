package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method("POST")
        url("/check")
        headers {
            header("Content-Type", "application/json")
        }
        body([
                age : value(consumer(regex('[2-9][0-9]')))
        ])
    }
    response {
        status 200
        headers {
            header("Content-Type", "application/json")
        }
        body([
            status: "OK"
            ])
    }
}