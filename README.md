# Coffee shop app

Example used to illustrate a presentation about hexagonal architecture. 

## Use case

![Use case](./doc/img/Use_case.png)

This examples focuses on "ordering & payment": a service receives orders from customers; 
it checks that the necessary ingredients are available, sends the order for preparation, and responds with the price.

## Interfaces

![Interfaces](./doc/img/Interfaces.png)

* The current service is in charge of resolving the price and the list of necessary ingredients according to a catalogue.
* It checks that the ingredients are available by calling another service which exposes current stock status.
* Orders are sent asynchronously to another service, dedicated to order preparation. 

![Adapters](./doc/img/Adapters.png)

This translates into 4 ports:
* A driving port, Order Controller, is in charge of receiving orders.
* A driven port, Catalogue, exposes the recipes along with their price.
* A driven port, Stock, exposes current stock status by calling the dedicated service.
* A driven port, Order Preparation, sends the order to the service in charge of order preparation.

## Next steps

0. Think about business rules for better naming and tests implementations
1. Add real models
2. Add DB repositories to adapters, file storage ?
3. Add comments where needed, remove where not needed
4. Add tests and logs to demonstrate the behaviour in console
5. Find better names if needed
6. Add annotations ?
