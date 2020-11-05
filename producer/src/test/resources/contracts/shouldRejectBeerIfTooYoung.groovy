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
                age : 17
        ])
    }
    response {
        status 200
        headers {
            header("Content-Type", "application/json")
        }
        body([
            status: "NOT_OK"
            ])
    }
}