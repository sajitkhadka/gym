import '@/app/ui/global.css'
import { inter } from '@/app/ui/fonts';

import Nav from "@/app/ui/layout/Nav";
import Provider from "@/app/lib/Provider";

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang='en'>
      <body>
        <Provider session={{}}>
          <div className='main'>
            <div className='gradient' />
          </div>
          <main className='app'>
            <Nav />
            {children}
          </main>
        </Provider>
      </body>
    </html>
  );
}
