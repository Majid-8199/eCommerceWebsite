interface Product {
    id: number;
    name: string;
    description: string;
    price: number;
    category: {
      name: string;
    };
    img: string;
    decodedImg?: string;
  }