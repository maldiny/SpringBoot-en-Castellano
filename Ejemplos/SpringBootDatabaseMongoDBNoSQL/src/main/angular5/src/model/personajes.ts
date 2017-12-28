export class Personajes {
  nombre: string;
  id: string;
  genero: string;
  edad: string;
  _links: Links;
}

export class Links {
	personajes: Href;
}

export class Href {
	href: String;
}

export class EmbeddedPersonajes {
	personajes: Personajes[];
}