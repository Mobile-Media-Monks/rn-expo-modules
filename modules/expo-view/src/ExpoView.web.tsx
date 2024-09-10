import * as React from 'react';

import { ExpoViewProps } from './ExpoView.types';

export default function ExpoView(props: ExpoViewProps) {
  return (
    <div>
      <span>{props.name}</span>
    </div>
  );
}
