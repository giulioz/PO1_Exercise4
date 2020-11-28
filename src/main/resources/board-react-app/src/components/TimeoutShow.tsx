import React, { useEffect, useState } from "react";

export function TimeoutShow({
  children,
  timeout = 300,
}: React.PropsWithChildren<{ timeout?: number }>) {
  const [show, setShow] = useState(false);
  useEffect(() => {
    const timer = setTimeout(() => setShow(true), timeout);
    return () => clearTimeout(timer);
  }, []);
  return show ? <> {children}</> : null;
}
