export class Book {
    constructor(public id: string,
                public title: string,
                public publisher: string,
                public author: string[],
                public category: string[],
                public arrivalDate: Date,
                public publishedDate: Date,
                public price: number,
                public imagename: string,
                public quantity: number) { }
  }
